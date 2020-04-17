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
import br.com.devteam.spotmusick.utils.SpotifyConstants
import br.com.devteam.spotmusick.viewmodel.AuthViewModel
import br.com.devteam.spotmusick.viewmodel.UserProfileViewModel
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: LoginFragmentBinding

    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    private val userProfileViewModel: UserProfileViewModel by lazy {
        ViewModelProvider(this).get(UserProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.fragment = this@LoginFragment
        binding.userCredentials = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

                val request = getAuthenticationRequest(AuthenticationResponse.Type.TOKEN)
                AuthenticationClient.openLoginActivity(
                    requireActivity(),
                    SpotifyConstants.AUTH_TOKEN_REQUEST_CODE,
                    request
                )
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


    private fun getAuthenticationRequest(type: AuthenticationResponse.Type): AuthenticationRequest {
        return AuthenticationRequest.Builder(
            SpotifyConstants.CLIENT_ID,
            type,
            SpotifyConstants.REDIRECT_URI
        )
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-email"))
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (SpotifyConstants.AUTH_TOKEN_REQUEST_CODE == requestCode) {
            val response = AuthenticationClient.getResponse(resultCode, data)
            val accessToken: String? = response.accessToken
//            fetchSpotifyUserProfile(accessToken)
            if (accessToken != null) {
                userProfileViewModel.cacheUserProfile(accessToken) {
                    if (it!!.success) {
                        Toast.makeText(
                            this.context,
                            "Dadsos atualizados.",
                            Toast.LENGTH_SHORT
                        ).show();
                        val intent_spotify_login = Intent("SPOTMUSICK_SEARCH")
                        intent_spotify_login.addCategory("SPOTMUSICK_SEARCH_TRACKS")
                        startActivity(intent_spotify_login)
                    } else {
                        Toast.makeText(
                            requireActivity().applicationContext,
                            it.userMessage,
                            Toast.LENGTH_LONG
                        ).show();
                    }
                }
            }
        }
    }

}
