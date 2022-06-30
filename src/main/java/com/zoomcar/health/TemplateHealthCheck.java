package com.zoomcar.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {
    String template;
    public TemplateHealthCheck(String template)
    {
        this.template = template;

    }
    @Override
    protected Result check() throws Exception {
        String saying =  String.format(template,"TEST");
        if(!saying.contains("TEST"))
        {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
