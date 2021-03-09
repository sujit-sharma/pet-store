package com.sujit.userservice;

import com.sujit.userservice.model.UserActivity;
import com.sujit.userservice.model.UserActivityDto;
import com.sujit.userservice.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
@RequiredArgsConstructor
public class UserActivityListener {
    private final UserActivityRepository userActivityRepository;

    @StreamListener(target = Sink.INPUT)
    public void processUserActivity(UserActivityDto userActivityDto) {
        UserActivity activity = new UserActivity();
        activity.setActivity(userActivityDto.getActivity());
        activity.setDate(userActivityDto.getDate());
        activity.setPetName(userActivityDto.getPetName());
        userActivityRepository.save(activity);
        log.info("User Activity Registered " + userActivityDto);
    }
}
