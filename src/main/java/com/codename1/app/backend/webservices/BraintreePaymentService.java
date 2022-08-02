package com.codename1.app.backend.webservices;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.codename1.app.backend.data.DishEntity;
import com.codename1.app.backend.data.DishRepository;
import com.codename1.app.backend.data.OrderEntity;
import com.codename1.app.backend.data.OrderLineEntity;
import com.codename1.app.backend.data.OrderLineRepository;
import com.codename1.app.backend.data.OrderRepository;
import com.codename1.app.backend.data.RestaurantEntity;
import com.codename1.app.backend.data.RestaurantRepository;
import com.codename1.app.backend.service.dao.Dish;
import com.codename1.app.backend.service.dao.Order;
import com.codename1.app.backend.service.dao.PurchaseResult;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/braintree")
public class BraintreePaymentService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderLineRepository orderLineRepository;
    
    @Autowired
    private DishRepository dishRepository;
    
    private WeakHashMap<String, BraintreeGateway> gatewayCache = new WeakHashMap<>();
        
    private BraintreeGateway getGateway(String auth) {
        BraintreeGateway bg = gatewayCache.get(auth);
        if(bg != null) {
            return bg;
        }
        RestaurantEntity r = restaurantRepository.findOne(auth);
        if(r != null) {
            bg = new BraintreeGateway(
                    Environment.SANDBOX,
                    r.getMerchantId(),
                    r.getPublicKey(),
                    r.getPrivateKey()
                  );
            gatewayCache.put(auth, bg);
            return bg;
        }
        return null;
    } 
    
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String getToken(@RequestParam(name = "auth") String auth) {
        return getGateway(auth).clientToken().generate();
    }
    public BigDecimal calculateAmount(Map<String, Integer> dishQuantity) {
        BigDecimal b = BigDecimal.ZERO;
        for(Map.Entry<String, Integer> d : dishQuantity.entrySet()) {
            DishEntity de = dishRepository.findOne(Long.parseLong(d.getKey()));
            b = b.add(new BigDecimal(d.getValue() * de.getPrice()));
        }
        return b;
    }


    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody PurchaseResult purchase(@RequestBody(required=true) Order i) {
        TransactionRequest requestT = new TransactionRequest()
            .amount(calculateAmount(i.getDishQuantity()))
            .paymentMethodNonce(i.getNonce())
            .options()
              .submitForSettlement(true)
              .done();

        Result<Transaction> result = getGateway(i.getAuth()).transaction().sale(requestT);
        if(result.isSuccess()) {
            StringBuilder order = new StringBuilder("Hello,\nthe following order was received and paid for with transaction id: ");
            order.append(result.getTransaction().getId());
            order.append("\n\nThis order should go to: ");
            order.append(i.getAddress().getName());
            order.append("\n");
            order.append(i.getAddress().getEmail());
            order.append("\n");
            order.append(i.getAddress().getPhone());
            order.append("\n");
            order.append(i.getAddress().getLine1());
            order.append("\n");
            order.append(i.getAddress().getLine2());
            order.append("\n");
            order.append(i.getAddress().getCity());
            order.append("\n");
            order.append(i.getAddress().getNotes());
            order.append("\n\nIt contains the following items:\n");
            
            // Store entry and email restaurant
            Set<OrderLineEntity> lines = new HashSet<>();
            for(Map.Entry<String,Integer> k : i.getDishQuantity().entrySet()) {
                OrderLineEntity ol  = new OrderLineEntity();
                ol.setAmount(k.getValue());
                DishEntity de = dishRepository.findOne(Long.parseLong(k.getKey()));
                ol.setDish(de);
                lines.add(ol);
                orderLineRepository.save(ol);
                order.append(k.getValue());
                order.append(" X ");
                order.append(de.getName());
                order.append("\n");
            }
            OrderEntity o = new OrderEntity();
            o.setDishQuanity(lines);
            o.setDate(new Date());
            o.setNotes(i.getNotes());
            o.setAddressNotes(i.getAddress().getNotes());
            o.setCity(i.getAddress().getCity());
            o.setEmail(i.getAddress().getEmail());
            o.setLine1(i.getAddress().getLine1());
            o.setLine2(i.getAddress().getLine2());
            o.setName(i.getAddress().getName());
            o.setPhone(i.getAddress().getPhone());
            orderRepository.save(o);
            
            
            RestaurantEntity r = restaurantRepository.findOne(i.getAuth());
            Email from = new Email("shai@codenameone.com");
            String subject = i.getAddress().getName() + " just ordered from your restaurant!";
            Email to = new Email(r.getRestaurantEmail());
            
            Content content = new Content("text/plain", order.toString());
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(r.getSendgridKey());
            Request request = new Request();
            try {
              request.method = Method.POST;
              request.endpoint = "mail/send";
              request.body = mail.build();
              Response response = sg.api(request);
              System.out.println(response.statusCode);
              System.out.println(response.body);
              System.out.println(response.headers);
            } catch (IOException ex) {
                // TODO...
                ex.printStackTrace();
            }            
        }
        return new PurchaseResult(result.isSuccess(), result.getMessage());
    }
}