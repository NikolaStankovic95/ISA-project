package com.pomoravskivrbaci.cinemareservations.utils;

import com.pomoravskivrbaci.cinemareservations.model.User;

public class UserUtils {
    public static int determineUserDiscount(User.UserRank rank) {
        switch(rank) {
            case NONE: return 0;
            case GOLD: return 30;
            case BRONZE: return 10;
            case SILVER: return 20;
            default: return 0;
        }
    }
}
