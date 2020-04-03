package br.com.devteam.spotmusick.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.domain.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private var profile: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        configure()
    }

    private fun configure() {
        val email = mAuth.currentUser?.email
        etEmail.setText(email)

        // Criar o evento para gravar os dados do usuário
        btSave.setOnClickListener { save() }

        // Recupera a referência para os perfis de usuário
        val profiles = database.getReference("profile")

        // Verifica se já existe um nó para esse usuário através
        // de uma pesquisa por e-mail
        val query = profiles.orderByChild("email").equalTo(email)

        // Inclui o evento de resultado na consulta (query)
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@ProfileActivity, "Consulta cancelada!", Toast.LENGTH_LONG).show()
            }

            // Quando a consulta retornar dados este método será acionado.
            // As consultas ao Firebase são assíncronas.
            override fun onDataChange(snapshot: DataSnapshot) {
                profile = snapshot.children.first().getValue(Profile::class.java)
                if (profile != null) {
                    etName.setText(profile?.name)
                    etPhone.setText(profile?.phone)
                }
            }
        })
    }

    private fun save() {
        // Cria um objeto com os dados da tela
        profile = Profile(
            email = etEmail.text.toString(),
            name = etName.text.toString(),
            phone = etPhone.text.toString()
        )

        val uid = mAuth.currentUser?.uid

        if (uid != null) {
            // Recupera a referência para o nó do usuário no perfil
            val userProfile = database.getReference("profile/$uid")
            userProfile.setValue(profile)
        } else {
            Toast.makeText(this@ProfileActivity, "Não foi possível recuperar a chave do usuário!", Toast.LENGTH_LONG).show()
        }
    }


}
