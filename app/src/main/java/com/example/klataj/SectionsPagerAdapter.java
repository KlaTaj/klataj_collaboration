package com.example.klataj;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 1:
                ContactsFragment contactsFragment  = new ContactsFragment();
                return contactsFragment;
            case 2:
                GroupeFragment groupeFragment = new GroupeFragment();
                return groupeFragment;
            case 3:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;
                default:
                    return null;

        }


    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle (int position){

        switch (position){
            case 0:
                return "Chat";
            case 1:
                return "Kontak";
            case 2:
                return "Gwoup";
            case 3:
                return "Aplikasyon";

            default:
                return null;


        }
    }


}
