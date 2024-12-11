package dam.pmdm.tarea2fvm;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import dam.pmdm.tarea2fvm.databinding.FragmentSettingsBinding;

/**
 * Fragmento de ajustes que permite al usuario cambiar el idioma y el tema de la aplicación.
 */
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SwitchCompat languageSwitch, themeSwitch;//Switches para cambiar el idioma entre inglés y español y activar/desactivar modo oscuro.

    private PreferencesHelper preferencesHelper;//Ayudante de preferencias para guardar y recuperar configuraciones de usuario.

    /**
     * Método que se llama para crear la vista asociada a este fragmento.
     *
     * @param inflater el LayoutInflater que se utiliza para inflar la vista.
     * @param container el contenedor padre al que se añadirá la vista.
     * @param savedInstanceState datos guardados previamente, si existen.
     * @return la vista raíz del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inicializar binding
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        languageSwitch = binding.switchLanguage;
        themeSwitch = binding.switchTheme;
        preferencesHelper = new PreferencesHelper(requireContext());

        // Establecer el estado inicial del Switch basado en la preferencia
        String currentLanguage = preferencesHelper.getLanguage();
        binding.switchLanguage.setChecked(currentLanguage.equals("en")); // Activa si el idioma es inglés
        themeSwitch.setChecked(preferencesHelper.isDarkMode());

        // Configurar el listener del Switch
        languageSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String newLanguage = isChecked ? "en" : "es"; // Inglés si está activado, Español si no
            preferencesHelper.setLanguage(newLanguage);
            setAppLocale(requireContext(), newLanguage);
            requireActivity().recreate(); // Reinicia la actividad para aplicar el idioma
        });

        // Configurar el listener del Switch
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferencesHelper.setTheme(isChecked);
            setTheme(isChecked);
        });

        // Retornar la vista raíz
        return binding.getRoot();
    }


    /**
     * Establece el idioma de la aplicación.
     *
     * @param context el contexto actual.
     * @param language el código del idioma a establecer.
     */
    private void setAppLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    /**
     * Establece el tema de la aplicación (modo oscuro o claro).
     *
     * @param isDarkMode true para modo oscuro, false para modo claro.
     */
    private void setTheme(boolean isDarkMode) {
        AppCompatDelegate.setDefaultNightMode(
                isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
        requireActivity().recreate(); // Reinicia la actividad para aplicar el tema
    }
}

