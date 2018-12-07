package r.andres.serviciosweb_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import r.andres.serviciosweb_android.entidades.usuarioBean;

public class DetallePanel extends AppCompatActivity implements View.OnClickListener , Response.Listener<String>, Response.ErrorListener{

    Button btnDAgregarP;
    Button btnDGrabar;
    TextView txtDUsuario;
    EditText txtDIdentificador,txtDDescripcion,txtDPreguntas;
    TableLayout tableDPreguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_panel);

        llamadaRecursos();
        btnDGrabar.setOnClickListener(this);
        btnDAgregarP.setOnClickListener(this);

    }



    public void llamadaRecursos(){
        btnDAgregarP = findViewById(R.id.btnDAgregarP);
        btnDGrabar = findViewById(R.id.btnDGrabar);
        txtDUsuario = findViewById(R.id.txtDUsuario);
        txtDIdentificador  = findViewById(R.id.txtDIdentificador);
        txtDDescripcion = findViewById(R.id.txtDDescripcion);
        txtDPreguntas = findViewById(R.id.txtDPreguntas);
        tableDPreguntas = findViewById(R.id.tableDPreguntas);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnDAgregarP:
                break;


            case R.id.btnDGrabar:
                break;
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

    }

    public void cargarWebService(){
        
    }
}
