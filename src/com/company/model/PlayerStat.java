package com.company.model;

/**
 * Rather than using a String to get our player stats, I've used an enum.
 *
 * This should help to reduce errors when looking for a player stat, since we have an enum value associated
 * Intellisense will allow us to accurately pick from this list, without fear of typos
 *
 * I only added it for the sake of consitency
 */
public enum PlayerStat {
    STR,
    DEX,
    CON,
    INT,
    WIS,
    CHA
}
