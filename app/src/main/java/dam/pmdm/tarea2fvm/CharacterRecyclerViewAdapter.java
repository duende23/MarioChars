package dam.pmdm.tarea2fvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam.pmdm.tarea2fvm.databinding.CharactersListItemBinding;

/**
 * Adaptador para un RecyclerView que muestra una lista de personajes.
 * Este adaptador gestiona la visualización de cada elemento en la lista de personajes.
 */

class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private final ArrayList<CharacterData> characters;
    private final Context context;

    /**
     * Constructor que inicializa el adaptador con una lista de personajes y el contexto.
     *
     * @param characters lista de personajes que se mostrarán
     * @param context    contexto de la actividad donde se utiliza el adaptador
     */

    public CharacterRecyclerViewAdapter(ArrayList<CharacterData> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    /**
     * Crea y devuelve un nuevo ViewHolder para un elemento del RecyclerView.
     *
     * @param parent   el ViewGroup al que se agregará la nueva vista
     * @param viewType el tipo de la vista que se creará (no utilizado en este caso)
     * @return un nuevo ViewHolder que contiene la vista del elemento
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharactersListItemBinding binding = CharactersListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new CharacterViewHolder(binding);
    }

    /**
     * Vincula los datos de un personaje a un ViewHolder.
     *
     * @param holder   el ViewHolder que se actualizará con los datos del personaje
     * @param position la posición del personaje en la lista
     */
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        CharacterData currentcharacter = this.characters.get(position);
        holder.bind(currentcharacter);

//        Manejar el evento de clic
        holder.itemView.setOnClickListener(view -> itemClicked(currentcharacter, view));
    }


    /**
     * Devuelve el número total de elementos en la lista de personajes.
     *
     * @return el tamaño de la lista de personajes
     */
    @Override
    public int getItemCount() {
        return characters.size();
    }

    /**
     * Gestiona el evento de clic en un elemento del RecyclerView.
     *
     * @param currentcharacter el personaje que fue clicado
     * @param view             la vista del elemento clicado
     */
    private void itemClicked(CharacterData currentcharacter, View view) {
        // Llama a la función characterClicked de MainActivity, pasando la vista
        ((MainActivity) context).characterClicked(currentcharacter, view);
    }
}
