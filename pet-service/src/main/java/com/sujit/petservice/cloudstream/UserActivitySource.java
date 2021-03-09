package com.sujit.petservice.cloudstream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserActivitySource {

    @Output("userActivityChannel")
    MessageChannel userActivity();
}
