public class View {

    public View() {
    }

    public void initialScreen(){
        //will draw initial screen, including UI
        System.out.println("There will be a UI here");
    }

    public void updateScreen(int screen){
        //will change the subscreen to the given screen
        System.out.println("Changing to screen " + screen);
    }
}
