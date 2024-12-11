package dam.pmdm.tarea2fvm;

/**
 * Representa un personaje con datos asociados como imagen, nombre, descripción y habilidades.
 * Esta clase es inmutable y proporciona métodos para acceder a sus atributos.
 */

class CharacterData {

    private final String image;
    private final String name;
    private final String description;
    private final String skills;

    /**
     * Construye una nueva instancia de CharacterData con los atributos especificados.
     *
     * @param image       la URL o ruta de la imagen del personaje
     * @param name        el nombre del personaje
     * @param description una breve descripción del personaje
     * @param skills      las habilidades o destrezas del personaje
     */

    public CharacterData(String image, String name, String description, String skills) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.skills = skills;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSkills() {
        return skills;
    }

}

