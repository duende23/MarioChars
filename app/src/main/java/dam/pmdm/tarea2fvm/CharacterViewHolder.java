package dam.pmdm.tarea2fvm;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import dam.pmdm.tarea2fvm.databinding.CharactersListItemBinding;


/**
 * ViewHolder para representar un elemento de la lista de personajes.
 * Este ViewHolder enlaza los datos del personaje con los componentes de la interfaz de usuario del elemento.
 */
class CharacterViewHolder extends RecyclerView.ViewHolder {

    private final CharactersListItemBinding binding;

    public CharacterViewHolder(CharactersListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Vincula los datos de un personaje a los componentes visuales del elemento.
     *
     * @param character el personaje cuyos datos se mostrarán
     */
    public void bind(CharacterData character) {
        Picasso.get()
                .load(character.getImage())
                .into(binding.ivCharacterImage);
        binding.tvName.setText(character.getName());

    }
}



