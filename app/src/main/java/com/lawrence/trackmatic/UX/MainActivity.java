package com.lawrence.trackmatic.UX;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lawrence.trackmatic.Entity.Coordinate;
import com.lawrence.trackmatic.Entity.Position;
import com.lawrence.trackmatic.Entity.Rover;
import com.lawrence.trackmatic.R;
import com.lawrence.trackmatic.constant.Constant;
import com.lawrence.trackmatic.enums.Command;
import com.lawrence.trackmatic.enums.Orientation;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Command[] commandsRoverA = new Command[] {
                Command.L,
                Command.M,
                Command.L,
                Command.M,
                Command.L,
                Command.M,
                Command.L,
                Command.M,
                Command.M };

        final Command[] commandsRoverB = new Command[] {
                Command.M,
                Command.M,
                Command.R,
                Command.M,
                Command.M,
                Command.R,
                Command.M,
                Command.R,
                Command.R,
                Command.M};

        Button btnStartRoverA = findViewById(R.id.btnStartRoverA);
        btnStartRoverA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                drawPlateau();

                Rover roverA = new Rover(new Position(new Coordinate(5, 5), Orientation.N));
                new ThreadDraw(roverA, commandsRoverA).start();
            }
        });

        Button btnStartRoverB = findViewById(R.id.btnStartRoverB);
        btnStartRoverB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                drawPlateau();

                Rover roverB = new Rover(new Position(new Coordinate(3, 3), Orientation.E));
                new ThreadDraw(roverB, commandsRoverB).start();
            }
        });
    }

    private void drawPlateau() {

        TableLayout plateau = findViewById(R.id.plateau);
        plateau.removeAllViews();

        for (int y = Constant.UPPER_MAX; y >= 0; y--) {
            TableRow tr = new TableRow(this);

            for (int x = 0; x <= Constant.RIGHT_MAX; x++) {
                ImageView iv = new ImageView(this);
                iv.setId(y * 10 + x);
                iv.setBackgroundResource(R.drawable.tile);
                tr.addView(iv);
            }
            plateau.addView(tr);
        }
    }

    class ThreadDraw extends Thread {

        private final Rover rover;
        private final Command[] commands;

        private ThreadDraw(Rover rover, Command[] commands) {
            this.rover = rover;
            this.commands = commands;
        }

        @Override
        public void run() {

            move(null);

            for (Command command : commands) {
                move(command);
            }
        }

        private void move(Command command) {
            Message msg = handler.obtainMessage();
            msg.getData().putSerializable("rover", rover);
            msg.getData().putSerializable("command", command);
            handler.sendMessage(msg);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            Rover rover = (Rover) msg.getData().getSerializable("rover");
            Command command = (Command) msg.getData().getSerializable("command");

            Position position;
            ImageView tile;

            if (command != null) {
                position = rover.getPosition();
                tile = getTile(position);
                tile.setBackgroundResource(R.drawable.tile);
                rover.move(command);
            }

            position = rover.getPosition();
            tile = getTile(position);
            tile.setBackgroundResource(getRoverResource(position.getOrientation()));

            TextView txtPosition =  findViewById(R.id.txtPosition);
            txtPosition.setText("Position: " + position.toString());
        }
    };

    private ImageView getTile(Position position) {
        int x = position.getCoordinate().getX();
        int y = position.getCoordinate().getY();
        return (ImageView) findViewById(y * 10 + x);
    }

    private int getRoverResource(Orientation orientation) {
        if (orientation == Orientation.N) {
            return R.drawable.rover_n;
        } else if (orientation == Orientation.E) {
            return R.drawable.rover_e;
        } else if (orientation == Orientation.W) {
            return R.drawable.rover_w;
        } else if (orientation == Orientation.S) {
            return R.drawable.rover_s;
        }
        return 0;
    }

}
