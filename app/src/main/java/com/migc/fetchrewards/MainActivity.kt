package com.migc.fetchrewards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.migc.fetchrewards.ui.theme.FetchRewardsTheme
import com.migc.fetchrewards.ui.theme.Typography
import com.migc.fetchrewards.ui.theme.backgroundColor
import com.migc.fetchrewards.ui.theme.mainColor
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = koinViewModel()
            val items = viewModel.items.collectAsState()

            FetchRewardsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)) {
                        items.value.forEach { group ->
                            stickyHeader {
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = mainColor,
                                    shadowElevation = 2.dp
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.list_id_text),
                                            fontSize = Typography.titleLarge.fontSize
                                        )
                                        Spacer(modifier = Modifier.width(1.dp))
                                        Text(
                                            text = group.key.toString(),
                                            fontSize = Typography.titleLarge.fontSize,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }

                            }
                            items(group.value) { item ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = item.id.toString(),
                                        textAlign = TextAlign.End
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        modifier = Modifier.weight(2f),
                                        text = item.name.toString()
                                    )
                                }
                            }
                            item { 
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                        }
                    }
                }
            }
        }
    }
}