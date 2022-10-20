package models.movie;

public class ThreeDMovie extends MovieType {
    private float moviePrice;
    private boolean has3DGoggles;

    public ThreeDMovie(float moviePrice) {
        super(moviePrice);
        this.has3DGoggles = false;
    }

    public void include3DGlasses(float ThreeDGlassesPrice) {
        this.moviePrice += ThreeDGlassesPrice;
        this.has3DGoggles = true;
    }
}
