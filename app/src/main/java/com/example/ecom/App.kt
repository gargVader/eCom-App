package com.example.ecom

import `in`.slanglabs.assistants.retail.AssistantConfiguration
import `in`.slanglabs.assistants.retail.SlangRetailAssistant
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val configuration: AssistantConfiguration = AssistantConfiguration.Builder()
            .setAPIKey("81aa2384c19e42b4af9a2f66703b38ab")
            .setAssistantId("9a835dada3b7401b9886b8d7b865e36c")
            .setEnvironment(SlangRetailAssistant.Environment.PRODUCTION)  // Change this to PRODUCTION once you've published the Assistant to production environment
            .build();
        SlangRetailAssistant.initialize(this, configuration);
    }

}