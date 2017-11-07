package ca.bcit.ass3.she_tan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // displays the database contents into the main activity
        final ListView list_events = (ListView) findViewById(R.id.list_events);

        final Event[] events = getEvents();

        ArrayAdapter<Event> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, events
        );

        list_events.setAdapter(arrayAdapter);

        list_events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Event evt = events[i];

                Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
                intent.putExtra("event", evt.getId());

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (cursor != null)
            cursor.close();

        if (db != null)
            db.close();
    }


    private Event[] getEvents() {
        SQLiteOpenHelper helper = new EventDbHelper(this);
        Event[] events = null;
        try {
            db = helper.getReadableDatabase();
            Cursor cursor= db.rawQuery("SELECT _eventId, NAME, DATE, TIME FROM EVENT_MASTER", null);

            int count = cursor.getCount();
            events = new Event[count];

            if (cursor.moveToFirst()) {
                int ndx=0;
                do {
                    Event evt = new Event(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                    events[ndx++] = evt;
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException sqlex) {
            String msg = "[MainActivity / getEvents] DB unavailable";
            msg += "\n\n" + sqlex.toString();

            Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            t.show();
        }
        return events;
    }
}
