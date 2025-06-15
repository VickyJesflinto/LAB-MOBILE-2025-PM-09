package com.example.instagramlikeapp;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<User> users = generateDummyUsers();

    private static ArrayList<User> generateDummyUsers() {
        ArrayList<User> list = new ArrayList<>();

        String username1 = "Zeus";
        int profile1 = R.drawable.profile1;
        ArrayList<Feed> posts1 = new ArrayList<>();
        posts1.add(new Feed(R.drawable.post1, "Thunder strikes again ⚡", username1, profile1));
        posts1.add(new Feed(R.drawable.post2, "Watching over Olympus ⛰️", username1, profile1));
        list.add(new User(username1, profile1, posts1));

        String username2 = "Poseidon";
        int profile2 = R.drawable.profile2;
        ArrayList<Feed> posts2 = new ArrayList<>();
        posts2.add(new Feed(R.drawable.post3, "Taming the waves 🌊", username2, profile2));
        posts2.add(new Feed(R.drawable.post4, "Seaside serenity 🐚", username2, profile2));
        list.add(new User(username2, profile2, posts2));

        String username3 = "Hades";
        int profile3 = R.drawable.profile3;
        ArrayList<Feed> posts3 = new ArrayList<>();
        posts3.add(new Feed(R.drawable.post5, "Lets kidnap Persephone 🌑", username3, profile3));
        posts3.add(new Feed(R.drawable.post6, "Silence is powerful 💀", username3, profile3));
        list.add(new User(username3, profile3, posts3));

        String username4 = "Athena";
        int profile4 = R.drawable.profile4;
        ArrayList<Feed> posts4 = new ArrayList<>();
        posts4.add(new Feed(R.drawable.post7, "Strategy before battle 🧠⚔️", username4, profile4));
        posts4.add(new Feed(R.drawable.post8, "Wisdom starts with calm mornings ☕📚", username4, profile4));
        list.add(new User(username4, profile4, posts4));

        String username5 = "Apollo";
        int profile5 = R.drawable.profile5;
        ArrayList<Feed> posts5 = new ArrayList<>();
        posts5.add(new Feed(R.drawable.post9, "Let the sunshine in ☀️🎵", username5, profile5));
        posts5.add(new Feed(R.drawable.post10, "Nature inspires the best verses 🌳🎻", username5, profile5));
        list.add(new User(username5, profile5, posts5));

        String username6 = "Hera";
        int profile6 = R.drawable.profile6;
        ArrayList<Feed> posts6 = new ArrayList<>();
        posts6.add(new Feed(R.drawable.post11, "Rule with grace and power 👑🔥", username6, profile6));
        posts6.add(new Feed(R.drawable.post12, "A queen never rests — she reigns 💫🦚", username6, profile6));
        list.add(new User(username6, profile6, posts6));

        String username7 = "Hephaestus";
        int profile7 = R.drawable.profile7;
        ArrayList<Feed> posts7 = new ArrayList<>();
        posts7.add(new Feed(R.drawable.post13, "Forged in fire, built to last 🔥🛠️", username7, profile7));
        posts7.add(new Feed(R.drawable.post14, "Even beauty begins at the anvil 💡⚙️", username7, profile7));
        list.add(new User(username7, profile7, posts7));

        String username8 = "Artemis";
        int profile8 = R.drawable.profile8;
        ArrayList<Feed> posts8 = new ArrayList<>();
        posts8.add(new Feed(R.drawable.post15, "Moonlit hunts and silent steps 🌕🏹", username8, profile8));
        posts8.add(new Feed(R.drawable.post16, "Wild and free, just like the forest 🌲🦌", username8, profile8));
        list.add(new User(username8, profile8, posts8));

        String username9 = "Demeter";
        int profile9 = R.drawable.profile9;
        ArrayList<Feed> posts9 = new ArrayList<>();
        posts9.add(new Feed(R.drawable.post17, "Where I walk, life blooms 🌾🌻", username9, profile9));
        posts9.add(new Feed(R.drawable.post18, "Harvest is sacred — nature is divine 🌽🌿", username9, profile9));
        list.add(new User(username9, profile9, posts9));

        String username10 = "Ares";
        int profile10 = R.drawable.profile10;
        ArrayList<Feed> posts10 = new ArrayList<>();
        posts10.add(new Feed(R.drawable.post19, "Glory is earned in battle ⚔️🔥", username10, profile10));
        posts10.add(new Feed(R.drawable.post20, "Strength. Honor. Victory. 💪🛡️", username10, profile10));
        list.add(new User(username10, profile10, posts10));



        return list;
    }
}
