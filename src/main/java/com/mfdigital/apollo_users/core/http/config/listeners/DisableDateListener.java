package com.mfdigital.apollo_users.core.http.config.listeners;

import com.mfdigital.apollo_users.core.http.config.annotations.DisableDate;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Slf4j
public class DisableDateListener {

    @PreUpdate
    public void setDisableDate(Object entity){
        Class<?> clazz = entity.getClass();

        try {
            Field statusField = clazz.getDeclaredField("status");
            statusField.setAccessible(true);

            boolean status = (boolean) statusField.get(entity);
            for (Field field : clazz.getDeclaredFields()){
                if (field.isAnnotationPresent(DisableDate.class)){
                    field.setAccessible(true);

                    field.set(entity, null);

                    if(!status){
                        field.set(entity, LocalDateTime.now());
                    }
                }





            }




        }catch (NoSuchFieldException | IllegalAccessException e){
            log.info("Error while setting @DisableDate: "+e.getMessage());
        }
    }
}
