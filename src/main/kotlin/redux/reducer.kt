package redux

import AddCourse
import AddGrade
import AddStudent
import AddStudentToCourse
import ChangeMode
import CourseAction
import MarkStudent
import ModeAction
import StudentAction
import react.Reducer
import tools.plus
import tools.replace

val appReducer: Reducer<AppState, RAction> =
    { state, action ->
        when (action) {
            is CourseAction -> AppState(
                courseReducer(state.courses, action),
                state.students,
                state.mode,
                state.grade,
                state.color
            )

            is StudentAction -> AppState(
                state.courses,
                studentReducer(state.students, action),
                state.mode,
                state.grade,
                state.color
            )

            is ModeAction -> AppState(
                state.courses,
                state.students,
                modeReducer(state.mode, action),
                state.grade,
                state.color
            )

            else -> state
        }
    }

val courseReducer: Reducer<CourseState, CourseAction> =
    { state, action ->
        when (action) {
            is MarkStudent -> state.replace(
                { _, course -> course.id == action.courseId },
                { course ->
                    course.copy(
                        marked = course.marked.replace(
                            { index, _ -> course.students[index] == action.studentId },
                            { mark -> !mark }
                        )
                    )
                }
            )

            is AddCourse -> state + action.course

            is AddStudentToCourse -> state.replace(
                { _, course -> course.id == action.courseId },
                { course ->
                    course.copy(
                        students = course.students + action.studentId,
                        marked = course.marked + false,
                        grades = course.grades + 1
                    )
                }
            )

            is AddGrade -> state.replace(
                { _, course -> course.id == action.courseId },
                { course ->
                    course.copy(
                        grades = course.grades.replace(
                            { index, _ -> course.students[index] == action.studentId },
                            { grade -> action.grade}
                        )
                    )
                }
            )
            else -> state
        }
    }

val studentReducer: Reducer<StudentState, StudentAction> =
    { state, action ->
        when (action) {
            is AddStudent -> state + action.student
            else -> state
        }
    }

val modeReducer: Reducer<ModeState, ModeAction> =
    { state, action ->
        when (action) {
            is ChangeMode -> !state
            else -> state
        }
    }
