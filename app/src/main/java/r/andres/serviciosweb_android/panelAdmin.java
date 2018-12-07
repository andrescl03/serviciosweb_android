package r.andres.serviciosweb_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import r.andres.serviciosweb_android.entidades.encuestaBean;
import r.andres.serviciosweb_android.utilitarios.Constantes;

public class panelAdmin extends AppCompatActivity implements View.OnClickListener , Response.Listener<String>, Response.ErrorListener{

    private int NUM_COLS=1;
    private int NUM_ROWS=4;

    String codigoObtenidoIntent;
    RequestQueue request;
    StringRequest StringRequest;

    TextView txtMIdentificador;
    TextView txtMDescripcion;
    Button btnPCrear;
    TableLayout tablaEncuestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_admin);
        obtenerElementosIntent();
        llamandoRecursos();
        btnPCrear.setOnClickListener(this);
        request = Volley.newRequestQueue(this);
        cargarWebService();


    }
    public void llamandoRecursos(){
        txtMIdentificador = findViewById(R.id.txtMIdentificador);
        txtMDescripcion = findViewById(R.id.txtMDescripcion);
        btnPCrear = findViewById(R.id.btnPCrear);
        tablaEncuestas = findViewById(R.id.tablaEncuestas);
    }

    public void obtenerElementosIntent(){
        Bundle datos = this.getIntent().getExtras();
        codigoObtenidoIntent = datos.getString(Constantes.CAMPO_USUARIOADMIN);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPCrear:

                break;
        }
    }
    public void cargarWebService() {


        String URL = "http://192.168.1.41:8080/Rest_Servicio/rest/restorant/verMisEncuestas?"
                + "cod="+codigoObtenidoIntent;

        URL = URL.replace(" ", "%20");
        StringRequest = new StringRequest(Request.Method.GET, URL, this, this);
        request.add(StringRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"error"+ error ,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onResponse(String response) {
        ArrayList<JSONObject> lista = new ArrayList<JSONObject>();

        try {

            JSONArray jsonArray = new JSONArray(response);

            ArrayList<encuestaBean> listaEncuesta = new ArrayList<encuestaBean>();

            for (int i = 0; i < jsonArray.length(); i++){
                lista.add(jsonArray.getJSONObject(i));
            }
            for (JSONObject obj : lista){
                encuestaBean x = new encuestaBean();
                x.setId(Integer.parseInt(obj.getString("id")));
                x.setDes(obj.getString("des"));
                x.setIdentificador(obj.getString("identificador"));
                listaEncuesta.add(x);
            }

            txtMIdentificador.setText(listaEncuesta.get(0).getIdentificador());
            txtMDescripcion.setText(listaEncuesta.get(0).getDes());

            tablaDinamica();



        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void tablaDinamica(){

        for(int i = 0; i<NUM_ROWS; i++){
            TableRow tableRow = new TableRow(this);
            tablaEncuestas.addView(tableRow);
            TextView objText = new TextView(this);
            objText.setText("identificador");
            tableRow.addView(objText);


            for(int j= 0; j<NUM_COLS; j++){
                Button button = new Button(this);


            }
        }

        for(int i = 0; i<NUM_ROWS; i++) {
            TableRow tableRow = new TableRow(this);
            tablaEncuestas.addView(tableRow);
            TextView objText = new TextView(this);
            objText.setText("Descripción");
            tableRow.addView(objText);

            for (int j = 0; j < NUM_COLS; j++) {
                Button button = new Button(this);


            }
        }
    }
}
