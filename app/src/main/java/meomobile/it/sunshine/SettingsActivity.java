package meomobile.it.sunshine;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    /**
     * Populate the activity with the top-level headers.
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);

    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here. Obbligatorio!
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || LocationFragment.class.getName().equals(fragmentName);
                //|| DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                //|| NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }

    public static class LocationFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Imposto quale xml caricare
            //PreferenceManager.setDefaultValues(getActivity(), R.xml.pref_general, false);
            // Lo carico
            addPreferencesFromResource(R.xml.pref_general);

            // Aggiungo l'evento a unit preference
            findPreference("unit_preference").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    //System.out.println(preference);
                    //System.out.println(newValue);
                    findPreference("unit_preference").setSummary(newValue.toString());
                    return true;
                }
            });

            // Aggiungi l'evento a location preference
            findPreference("location_preference").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    findPreference("location_preference").setSummary(newValue.toString());
                    return false;
                }
            });
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            System.out.println("Cambio qualcosa");
        }
    }


}
