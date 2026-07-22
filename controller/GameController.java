package controller;

public class GameController {
  private Game model; 
  private GameView view; 

  public GameController(Game model, GameView view)
  {
    this.model = model; 
    this.view = view; 
  }
}
