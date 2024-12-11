package dam.pmdm.tarea2fvm;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Clase auxiliar para gestionar las preferencias de la aplicación, como idioma y tema.
 */
class PreferencesHelper {
    private static final String PREF_NAME = "app_preferences";//Nombre del archivo de preferencias compartidas
    private static final String KEY_LANGUAGE = "language";//Clave para la preferencia del idioma
    private static final String KEY_THEME = "theme";//Clave para la preferencia del tema (modo oscuro o claro).

    private SharedPreferences sharedPreferences;

    /**
     * Constructor de la clase PreferencesHelper.
     *
     * @param context contexto de la aplicación utilizado para acceder a las preferencias compartidas.
     */
    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Establece el idioma de la aplicación en las preferencias.
     *
     * @param language código del idioma a guardar, como "es" para español o "en" para inglés.
     */
    public void setLanguage(String language) {
        sharedPreferences.edit().putString(KEY_LANGUAGE, language).apply();
    }


    /**
     * Recupera el idioma configurado en las preferencias.
     *
     * @return el código del idioma configurado, o "es" como valor predeterminado si no está configurado.
     */
    public String getLanguage() {
        return sharedPreferences.getString(KEY_LANGUAGE, "es"); // "es" es el idioma por defecto
    }

    /**
     * Establece el tema de la aplicación (modo oscuro o claro) en las preferencias.
     *
     * @param isDarkMode true para modo oscuro, false para modo claro.
     */
    public void setTheme(boolean isDarkMode) {
        sharedPreferences.edit().putBoolean(KEY_THEME, isDarkMode).apply();
    }

    /**
     * Recupera el tema configurado en las preferencias.
     *
     * @return true si el modo oscuro está habilitado, false si está en modo claro.
     */
    public boolean isDarkMode() {
        return sharedPreferences.getBoolean(KEY_THEME, false); // Por defecto, tema claro
    }
}