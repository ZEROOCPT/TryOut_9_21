package com.example.tryout_9_21;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Default tampilkan FragmentLaliga
        loadFragment(new FragmentLaliga());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.menu_laliga) {
                selectedFragment = new FragmentLaliga();
            } else if (item.getItemId() == R.id.menu_premier) {
                selectedFragment = new FragmentEpl();
            } else if (item.getItemId() == R.id.menu_profile) {
                selectedFragment = new FragmentProfile();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }


            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
