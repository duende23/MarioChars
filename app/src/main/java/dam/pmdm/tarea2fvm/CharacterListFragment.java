package dam.pmdm.tarea2fvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import dam.pmdm.tarea2fvm.databinding.FragmentCharacterListBinding;


public class CharacterListFragment extends Fragment {

    //Bandera estática para controlar si es el primer inicio de la aplicación.
    //private static boolean isFirstLaunch = true; // Bandera estática

    private FragmentCharacterListBinding binding; // Binding para el layout
    private ArrayList<CharacterData> characters; // Lista de caracteres
    private CharacterRecyclerViewAdapter adapter; // Adaptador del RecyclerView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflar el layout utilizando el binding
        binding = FragmentCharacterListBinding.inflate(inflater, container, false);

        Snackbar.make(binding.getRoot(), getString(R.string.welcome_message), Snackbar.LENGTH_LONG).show();
       /* // Mostrar Snackbar solo al tomar los datos de la aplicación por primera vez
        if (isFirstLaunch) {
            Snackbar.make(binding.getRoot(), getString(R.string.welcome_message), Snackbar.LENGTH_LONG).show();
            isFirstLaunch = false; // Actualizar la bandera
        }*/

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa la lista de personajes
        loadCharacters(); // Cargar los personajes
        // Configurar el RecyclerView
        adapter = new CharacterRecyclerViewAdapter(characters, getActivity());
        binding.rvCharacterList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvCharacterList.setAdapter(adapter);


     }

    private void loadCharacters() {

        characters = new ArrayList<CharacterData>();

        characters.add(new CharacterData(
                "https://i.pinimg.com/originals/1c/ee/d0/1ceed098558380f5c4739bb878bd7bce.png",
                "Mario",
                getString(R.string.mario_description),
                getString(R.string.mario_skills)
        ));

        characters.add(new CharacterData(
                "https://i.pinimg.com/originals/2f/cc/0d/2fcc0d238a5be0222921b29a9ffe3071.png",
                "Luigi",
                getString(R.string.luigi_description),
                getString(R.string.luigi_skills)

        ));

        characters.add(new CharacterData(
                "https://i0.wp.com/eltallerdehector.com/wp-content/uploads/2022/08/princess-peach-png.png?resize=600%2C600&ssl=1",
                "Princesa Peach",
                getString(R.string.peach_description),
                getString(R.string.peach_skills)
        ));

        characters.add(new CharacterData(
                "https://static.wikia.nocookie.net/doblaje/images/7/7e/Bowser_-_Mario_Party_10.png/revision/latest?cb=20190208052504&path-prefix=es",
                "Bowser",
                getString(R.string.bowser_description),
                getString(R.string.bowser_skills)
        ));

        characters.add(new CharacterData(
                "https://static.wikia.nocookie.net/mario/images/4/4d/Yoshi_-_Mario_Party_10.png/revision/latest/scale-to-width-down/200?cb=20171012174642&path-prefix=es",
                "Yoshi",
                getString(R.string.yoshi_description),
                getString(R.string.yoshi_skills)
        ));

        characters.add(new CharacterData(
                "https://images.wikidexcdn.net/mwuploads/esssbwiki/4/4f/latest/20230118173635/Toad_Mario_Party_Superstars.png",
                "Toad",
                getString(R.string.toad_description),
                getString(R.string.toad_skills)
        ));

        characters.add(new CharacterData(
                "https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/5/5f/latest/20230214044524/Donkey_Kong_SSB4.png/1200px-Donkey_Kong_SSB4.png",
                "Donkey Kong",
                getString(R.string.donkey_kong_description),
                getString(R.string.donkey_kong_skills)
        ));

        characters.add(new CharacterData(
                "https://static.wikia.nocookie.net/heroess/images/b/b8/Rosalina_SSBU.png/revision/latest?cb=20190130033221&path-prefix=es",
                "Rosalina",
                getString(R.string.rosalina_description),
                getString(R.string.rosalina_skills)
        ));

        characters.add(new CharacterData(
                "https://i.pinimg.com/originals/31/e2/79/31e2794f4a3280333ebde8f2169ff040.png",
                "Wario",
                getString(R.string.wario_description),
                getString(R.string.wario_skills)
        ));

        characters.add(new CharacterData(
                "https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/5/54/latest/20220531161025/Bowser_Jr._en_Mario_Rabbids_Kingdom_Battle.png/1200px-Bowser_Jr._en_Mario_Rabbids_Kingdom_Battle.png",
                "Bowser Jr.",
                getString(R.string.bowser_jr_description),
                getString(R.string.bowser_jr_skills)
        ));

    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_fragment_list);
        }
    }

}
