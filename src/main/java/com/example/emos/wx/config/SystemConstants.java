package com.example.emos.wx.config;

/*
 * @ClassName:SystemConstants
 * @descript:
 * @author: song
 * @date:2021/12/13
 */

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SystemConstants {
    public String attendanceStartTime;
    public String attendanceTime;
    public String attendanceEndTime;
    public String closingStartTime;
    public String closingTime;
    public String closingEndTime;
}
