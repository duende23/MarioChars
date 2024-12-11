package dam.pmdm.tarea2fvm;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.Locale;

import dam.pmdm.tarea2fvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;//Controlador de navegación para gestionar la navegación entre fragmentos.
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;//Toggle para controlar el menú lateral (drawer).

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
     /* EdgeToEdge.enable(this);
         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/


        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //Guarda y recupera la configuración al iniciar la aplicación
        PreferencesHelper preferencesHelper = new PreferencesHelper(this);

        // Aplica el idioma guardado
        String language = preferencesHelper.getLanguage();
        setAppLocale(this, language);

        // Aplica el tema guardado
        if (preferencesHelper.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(binding.getRoot());

        // Vincular la Toolbar
        setSupportActionBar(binding.toolbar);

        // Inicialización del controlador de navegación
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        //Lo utilizaremos para cambiar de menu hamburguesa a la flecha retroceso cuando cambiemos de fragment
        navController.addOnDestinationChangedListener(this::onChangeView);

        // Configurar la navegación
        configureNavigation();
        // Configurar menú toggle
        configureToggleMenu();

        // Configurar el icono del menú en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Establece el idioma de la aplicación.
     *
     * @param mainActivity actividad principal
     * @param language     código del idioma a establecer
     */
    private void setAppLocale(MainActivity mainActivity, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }


    /**
     * Cambia la apariencia del menú según el destino actual de navegación.
     *
     * @param navController  controlador de navegación
     * @param navDestination destino actual de navegación
     * @param bundle         datos adicionales de navegación, si existen
     */
    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {

        if (toggle == null) return;
        if (navDestination.getId() == R.id.characterDetailFragment) {
            toggle.setDrawerIndicatorEnabled(false);
        } else toggle.setDrawerIndicatorEnabled(true);
    }


    /**
     * Configura el menú lateral con un ActionBarDrawerToggle.
     */
    private void configureToggleMenu() {
        // Configurar el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Configura la navegación dentro del menú lateral.
     */
    private void configureNavigation() {
        NavigationUI.setupWithNavController(binding.navView, navController);
        // Manejar la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {// Acción para la opción "Home"
                navController.navigate(R.id.characterListFragment); // Navegar al fragmento de inicio
            } else if (item.getItemId() == R.id.nav_settings) {// Acción para la opción "Settings"
                navController.navigate(R.id.settingsFragment); // Navegar al fragmento de ajustes
            }
            binding.drawerLayout.closeDrawers();

            return true;
        });


          /*  if (menuItem.getItemId() == R.id.nav_home) {
                navController.navigate(R.id.characterListFragment); // Navegar al fragmento de inicio
            }
            binding.drawerLayout.closeDrawers(); // Cerrar el menú
            return true;
        });*/

        // Maneja la opción de perfil del header del menú
        ImageView profileImageView = binding.navView.getHeaderView(0).findViewById(R.id.header_image);

        profileImageView.setOnClickListener(v -> {
            navController.navigate(R.id.fragmentProfile); // Navegar al fragmento de perfil
            binding.drawerLayout.closeDrawers(); // Cerrar el menú
        });
    }


    /**
     * Método que se llama cuando se hace clic en un personaje.
     *
     * @param character personaje que fue clicado
     * @param view      vista asociada al clic
     */
    public void characterClicked(CharacterData character, View view) {
        // Crear un Bundle para pasar los datos al GameDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName()); // Pasa el nombre del personaje
        bundle.putString("description", character.getDescription());// Pasa la descripción del personaje
        bundle.putString("skills", character.getSkills());// Pasa las habilidades del personaje
        bundle.putString("image", character.getImage()); // Pasa la imagen del personaje

        // Navegar al CharacterDetailFragment con el Bundle
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }

    /**
     * Infla el menú de opciones.
     *
     * @param menu el menú de opciones
     * @return true si se infla correctamente
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú; esto agrega los elementos a la barra de herramientas
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    /**
     * Maneja los clics en los elementos del menú de opciones.
     *
     * @param item elemento del menú que fue clicado
     * @return true si el clic se maneja correctamente
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Obtener el ID del elemento seleccionado
        int id = item.getItemId();

        if (id == R.id.action_info) {
            // Mostrar el AlertDialog
            showAboutDialog();
            return true;
        }

        // Manejar clics en el icono del menú
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Muestra un cuadro de diálogo con información sobre la aplicación.
     */
    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.action_info));
        builder.setMessage(getString(R.string.dialog_info));

        // Botón de cierre
        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Maneja la navegación al presionar el botón de retroceso en la barra de acción.
     *
     * @return true si la navegación es exitosa, false en caso contrario
     */
    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el metodo navigateUp del NavController
        // return navController.navigateUp() || super.onSupportNavigateUp();

        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
            return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp();

        }
        return super.onSupportNavigateUp();

    }


}