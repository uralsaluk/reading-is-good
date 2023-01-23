package com.ural.readingisgood.orderservice.config;

import com.ural.readingisgood.orderservice.util.ContextUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class CustomAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {


        return Optional.of(ContextUtil.getUserId());
    }
}
