package component


import csstype.*
import emotion.react.css
import react.*
import react.dom.html.InputType
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.redux.useSelector
import redux.AppState

/*
external interface ColorGrade : Props {
    var listcolor: Array<Color>
    var setColor: (Int, Color) -> Unit
}

 */
/*
val CColor = FC<Props>("ChangeColor") { props ->
    var listcolor = useSelector { state: AppState -> state.color }
    var setColor: (Int, Color) -> Unit
    listOf(1,2,3,4,5).mapIndexed { _index, grade ->
        label { +grade.toString() }
        input {
            type = InputType.color
            value = listcolor
            onChange = { props.setColor(_index, Color(it.target.value)) }
        }
    }
}

 */