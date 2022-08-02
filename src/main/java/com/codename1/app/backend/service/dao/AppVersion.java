package com.codename1.app.backend.service.dao;

public class AppVersion {
    private double currentVersion;
    private double oldestSupportedVersion;

    public AppVersion() {
    }
    
    public AppVersion(double currentVersion, double oldestSupportedVersion) {
        this.currentVersion = currentVersion;
        this.oldestSupportedVersion = oldestSupportedVersion;
    }

    
    /**
     * @return the currentVersion
     */
    public double getCurrentVersion() {
        return currentVersion;
    }

    /**
     * @return the oldestSupportedVersion
     */
    public double getOldestSupportedVersion() {
        return oldestSupportedVersion;
    }
}
