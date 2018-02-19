package br.com.pos.cacaestudio;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pos.cacaestudio.adapter.EstudiosAdapter;
import br.com.pos.cacaestudio.fragments.AboutDialog;
import br.com.pos.cacaestudio.modelo.Estudio;

public class ListaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Lista de Estúdios
        Estudio estudio1 = new Estudio("Estúdio Tupira","Rua YYY, Santo Agostinho", "25");
        Estudio estudio2 = new Estudio("Estúdio Sonora", "Rua XXX, Centro", "30");
        List<Estudio> estudios = new ArrayList<Estudio>();
        estudios.add(estudio1);
        estudios.add(estudio2);

        //Adapter da Lista
        ListView listaEstudios = findViewById(R.id.lista_estudios);
        EstudiosAdapter adapter = new EstudiosAdapter(this,estudios);
        listaEstudios.setAdapter(adapter);

        //View
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //para criar um menu de contexto
        registerForContextMenu(listaEstudios);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem itemAgendar = menu.add("Agendar");
        MenuItem itemAvaliar = menu.add("Avaliar");

        itemAgendar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(ListaActivity.this, AgendarActivity.class);
                startActivity(intent);
                return false;
            }
        });

        itemAvaliar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(ListaActivity.this, AvaliarActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dados) {
            Intent intent = new Intent(ListaActivity.this,CadastrarActivity.class);
            intent.putExtra("origem","main");
            startActivity(intent);
        } else if (id == R.id.nav_agendados) {
            Intent intent = new Intent(ListaActivity.this, AgendadosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sobre) {
            AboutDialog.showAbout(getSupportFragmentManager());
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
