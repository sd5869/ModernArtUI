package course.lab.modernartui.activities;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import course.lab.modernartui.R;
import course.lab.modernartui.dialogs.MoreInformationDialog;
import java.util.Random;
/**
 * Contains the main activity of the application. It contains a palette that holds several
 * rectangular views, each with its own background color and a seek bar at the bottom, dragging
 * which changes the color of each rectangular (except the white and gray ones).
 *
 * Author- Siddharth Dungarwal
 */
public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    private RelativeLayout palette;

    /**
     * Called when the activity is starting.
     *
     * Sets up the main activity content view and registers a change handler for the activities
     * seek bar.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it
     *                           most
     *                           recently supplied.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        palette = (RelativeLayout) findViewById(R.id.palette);
        SeekBar seek = (SeekBar) findViewById(R.id.seekBar);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            /*
             * Notification that the seek bar progress level has changed.
             *
             * When the seek bar state changes, loop through the child's of the palette view and
             * change each individuals view color based on randomness,generate colours from VIBGYOR.
             * Ignores views that are white.
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                for (int i = 0; i < palette.getChildCount(); i++) {
                    View child = palette.getChildAt(i);
                    int r=0,g=0,b=0;

                    Random randomGenerator = new Random();
                    int randomInt = randomGenerator.nextInt(7);
                    if (randomInt==0)
                    {r=72;g=61;b=139;}
                    if (randomInt==1)
                    {r=111;g=0;b=255;}
                    if (randomInt==2)
                    {r=g=0;b=255;}
                    if (randomInt==3)
                    {r=0;g=255;b=0;}
                    if (randomInt==4)
                    {r=255;g=255;b=0;}
                    if (randomInt==5)
                    {r=255;g=165;b=0;}
                    if (randomInt==6)
                    {r=255;g=b=0;}
                    int originalColor = Color.parseColor( ( String ) child.getTag() );
                    if ( getResources().getColor( R.color.white ) != originalColor) {
                        child.setBackgroundColor( Color.rgb(r ,g ,b ) );
                        child.invalidate();
                   }
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    /**
     * Initialize the contents of the Activity's standard options menu.  Menu items are placed
     * in to <var>menu</var>.
     *
     * <p>This is only called once, the first time the options menu is
     * displayed.</p>
     *
     * @param menu The options menu in which the items are placed.
     * @return Returns true for the menu to be displayed;
     * if it returns false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * When the user clicks the more information options menu.
     * Shows the more information dialog fragment.
     * @param item The menu item that was clicked
     */
    public void showDialog(MenuItem item) {

        new MoreInformationDialog().show(getFragmentManager(), TAG);
    }
}
