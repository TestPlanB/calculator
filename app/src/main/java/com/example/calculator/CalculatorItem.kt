package com.example.calculator

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.Shapes


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalculatorItem(titles: List<String>, onClick: (title: String) -> Unit) {

    LazyVerticalGrid(
        cells = GridCells.Fixed(4),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {

        itemsIndexed(titles) { index, _ ->
            Text(
                text = titles[index],
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color(229,168,70), shape = Shapes.medium)
                    .padding(10.dp)
                    .clickable {
                        onClick.invoke(titles[index])
                    }
            )

        }
    }


}