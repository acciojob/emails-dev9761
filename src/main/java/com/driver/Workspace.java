package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

class Workspace extends Gmail {
    private List<Meeting> calendar;

    public Workspace(String emailId) {
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        int maxMeetings = 0;
        int currentMeetings = 0;
        List<Meeting> sortedCalendar = new ArrayList<>(calendar);
        sortedCalendar.sort((m1, m2) -> m1.getEndTime().compareTo(m2.getEndTime()));

        for (Meeting meeting : sortedCalendar) {
            if (meeting.getStartTime().compareTo(meeting.getEndTime()) >= 0) {
                continue;
            }
            currentMeetings++;
            if (currentMeetings > maxMeetings) {
                maxMeetings = currentMeetings;
            }
            for (Meeting nextMeeting : sortedCalendar) {
                if (nextMeeting.getStartTime().compareTo(meeting.getEndTime()) >= 0) {
                    break;
                }
                currentMeetings--;
            }
        }
        return maxMeetings;
    }
}
