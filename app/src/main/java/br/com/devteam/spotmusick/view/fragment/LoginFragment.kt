package br.com.devteam.spotmusick.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.databinding.LoginFragmentBinding
import br.com.devteam.spotmusick.view.activity.SpotifyLoginActivity
import br.com.devteam.spotmusick.viewmodel.AuthViewModel


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: LoginFragmentBinding

    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.login_fragment, container, false)
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.fragment = this@LoginFragment
        binding.userCredentials = viewModel
        binding.lifecycleOwner = this


//        val bt_entrar = view.findViewById<Button>(R.id.bt_entrar)
//        bt_entrar.setOnClickListener(){
//            findNavController().navigate(R.id.action_loginFragment_to_redefinePasswordFragment)
//        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun login(view: View) {
        viewModel.login {
            if (it!!.success) {
                println("email: ${viewModel.email.value}, password: ${viewModel.password.value}")

                Toast.makeText(
                    requireActivity().applicationContext,
                    "Login efetuado com sucesso.",
                    Toast.LENGTH_SHORT
                ).show();

                val intent_spotify_login = Intent("SPOTMUSICK_LOGIN")
                intent_spotify_login.addCategory("SPOTMUSICK_LOGIN_CATEGORY")
                startActivity(intent_spotify_login)
//                startActivity(Intent(requireActivity(), SpotifyLoginActivity::class.java))
            } else {
                Toast.makeText(
                    requireActivity().applicationContext,
                    it.userMessage,
                    Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    fun navToRedefinirSenhaFragment(view: View) {
        findNavController().navigate(R.id.action_loginFragment_to_redefinePasswordFragment)
    }

    fun navToRegisterFragment(view: View) {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

}
