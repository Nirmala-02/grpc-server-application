package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "preference_details")
public class PreferenceData {

    @Column(name = "preferenceId")
    private int preferenceId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private int customerId;
    @Column(name = "preferredLanguage")
    private String preferredLanguage;
    @Column(name = "preferredContactMethod")
    private String preferredContactMethod;
    @Column(name = "marketingConsent")
    private boolean marketingConsent;
    @Column(name = "paperlessStatements")
    private boolean paperlessStatements;

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getPreferredContactMethod() {
        return preferredContactMethod;
    }

    public void setPreferredContactMethod(String preferredContactMethod) {
        this.preferredContactMethod = preferredContactMethod;
    }

    public boolean isMarketingConsent() {
        return marketingConsent;
    }

    public void setMarketingConsent(boolean marketingConsent) {
        this.marketingConsent = marketingConsent;
    }

    public boolean isPaperlessStatements() {
        return paperlessStatements;
    }

    public void setPaperlessStatements(boolean paperlessStatements) {
        this.paperlessStatements = paperlessStatements;
    }
}
