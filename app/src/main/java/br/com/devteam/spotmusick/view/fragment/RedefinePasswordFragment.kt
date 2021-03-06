package br.com.devteam.spotmusick.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.repository.AuthRepository
import br.com.devteam.spotmusick.databinding.RedefinePasswordFragmentBinding
import br.com.devteam.spotmusick.viewmodel.AuthViewModel

class RedefinePasswordFragment : Fragment() {

    companion object {
        fun newInstance() =
            RedefinePasswordFragment()
    }

    private lateinit var binding: RedefinePasswordFragmentBinding

    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RedefinePasswordFragmentBinding.inflate(inflater, container, false)

        binding.viewData = viewModel
        binding.fragment = this@RedefinePasswordFragment
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    fun resetEmail(view: View) {

        viewModel.sendPasswordResetEmail {
            if (it!!.success) {
                println("email: ${viewModel.email.value}")

                Toast.makeText(
                    requireActivity().applicationContext,
                    "E-mail enviado com sucesso.",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_redefinePasswordFragment_to_loginFragment)
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
