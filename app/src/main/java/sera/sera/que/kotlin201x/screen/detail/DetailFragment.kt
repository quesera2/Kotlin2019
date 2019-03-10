package sera.sera.que.kotlin201x.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.koin.android.viewmodel.ext.android.viewModel
import sera.sera.que.kotlin201x.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()

    private lateinit var binding: DetailFragmentBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.loadUrl("https://ja.wikipedia.org/w/index.php?curid=${args.page.pageid}")
    }
}
