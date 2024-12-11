package dam.pmdm.tarea2fvm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad de introducción (Splash) que se muestra cuando se inicia la aplicación.
 * Esta actividad muestra una pantalla de bienvenida y, dependiendo de la versión de la API de Android,
 * maneja la transición hacia la MainActivity de manera diferente.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Constante que define el tiempo de espera para mostrar la pantalla Splash (en milisegundos).
     * Se establece en 2000 milisegundos (2 segundos).
     */
    private static final int SPLASH_TIME_OUT = 2000; // 2 segundos para la Splash


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Si la versión de la API es inferior a 31, mostraremos la Splash personalizada
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            // Si la versión es inferior a Android 12 (API 31), manejamos la Splash manualmente
            setContentView(R.layout.activity_splash);

            /**
             * Método que se ejecuta después del retraso configurado.
             * Inicia la MainActivity y termina la SplashActivity.
             */
            // Usamos un Handler para retrasar el inicio de la MainActivity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Después del tiempo de la Splash, se inicia la MainActivity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();  // Terminamos la SplashActivity para que no vuelva a abrirse
                }
            }, SPLASH_TIME_OUT);
        } else {
            // Si la versión es 31 o superior, dejamos que Android maneje la Splash automáticamente
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Terminamos la SplashActivity
        }
    }
}