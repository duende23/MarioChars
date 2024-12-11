package dam.pmdm.tarea2fvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import dam.pmdm.tarea2fvm.databinding.FragmentCharacterDetailBinding;


public class CharacterDetailFragment extends Fragment {


    private FragmentCharacterDetailBinding binding; // Binding para el layout


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener datos del argumento que inicia este fragmento
        if (getArguments() != null) {
            String image = getArguments().getString("image");
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            String skills = getArguments().getString("skills");

            // Asignar los datos a los componentes
            Picasso.get()
                    .load(image)
                    .into(binding.ivImageCharacterDetails);
            binding.tvNameCharacterDetail.setText(name);
            binding.tvCharacterDescription.setText(description);
            binding.tvCharacterSkills.setText(skills);

        }
        Toast.makeText(getContext(), getString(R.string.character_selection) + binding.tvNameCharacterDetail.getText(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_fragment_details);
        }
    }


}