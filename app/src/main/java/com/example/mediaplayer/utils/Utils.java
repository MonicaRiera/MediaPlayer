package com.example.mediaplayer.utils;

import com.example.mymusicplayer.data.Song;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Song> getListData() {

        String[] listImages = {"brasilsamba", "countryboy", "india", "littleplanet", "psychedelic", "relaxing", "theelevatorbossanova"};

        String[] listSongs = {"bensoundbrazilsamba", "bensoundcountryboy", "bensoundindia",
                "bensoundlittleplanet", "bensoundpsychedelic", "bensoundrelaxing", "bensoundtheelevatorbossanova"};

        String[] listTitles = {"Brazil Samba", "Country Boy", "India",
                "Little Planet", "Psychedelic", "Relaxing", "The Elevator Bossa Nova"};

        int[] listDurations = {240000, 207000, 253000, 396000, 236000, 288000, 254000};

        String[] listCountries = {"Brazil", "USA", "India", "Iceland", "South Korea", "Indonesia", "Brazil"};

        String[] listComments = {"Samba is a Brazilian musical genre and dance style, with its roots in Africa via the West African slave trade and African religious traditions, particularly of Angola",
                "Country music is a genre of American popular music that originated in the Southern United States in the 1920s",
                "The music of India includes multiple varieties of folk music, pop, and Indian classical music. India's classical music tradition, including Hindustani music and Carnatic, has a history spanning millennia and developed over several eras",
                "The music of Iceland includes vibrant folk and pop traditions. Wellknown artists from Iceland include medieval music group Voces Thules, alternative rock band The Sugarcubes, singers Björk and Emiliana Torrini, post-rock band Sigur Rós and indie folk/indie pop band Of Monsters and Men",
                "The Music of South Korea has evolved over the course of the decades since the end of the Korean War, and has its roots in the music of the Korean people, who have inhabited the Korean peninsula for over a millennium. Contemporary South Korean music can be divided into three different main categories: Traditional Korean folk music, popular music, or K-pop, and Western-influenced non-popular music",
                "The music of Indonesia demonstrates its cultural diversity, the local musical creativity, as well as subsequent foreign musical influences that shaped contemporary music scenes of Indonesia. Nearly thousands of Indonesian islands having its own cultural and artistic history and character",
                "Samba is a Brazilian musical genre and dance style, with its roots in Africa via the West African slave trade and African religious traditions, particularly of Angola"};

        List<Song> mySongList = new ArrayList<>();

        for (int i = 0; i < listTitles.length; i++ ) {
            Song song = new Song(listImages[i], listSongs[i], listTitles[i], listDurations[i], listCountries[i], listComments[i]);
            mySongList.add(song);
        }
        return mySongList;

    }




}
