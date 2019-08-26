package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import ExpenseService.Project.*;
import ExpenseService.Exception.*;

import static ExpenseService.Expense.ExpenseType.*;
import static ExpenseService.Project.ProjectType.*;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
    	Project project = new Project(INTERNAL, "test");
        // when
    	ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(INTERNAL_PROJECT_EXPENSE, expenseType);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
    	Project project = new Project(EXTERNAL, "Project A");
        // when
    	ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(EXPENSE_TYPE_A, expenseType);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
    	// given
    	Project project = new Project(EXTERNAL, "Project B");
        // when
    	ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(EXPENSE_TYPE_B, expenseType);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
    	// given
    	Project project = new Project(EXTERNAL, "Project C");
        // when
    	ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(OTHER_EXPENSE, expenseType);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
    	// given
    	Project project = new Project(UNEXPECTED_PROJECT_TYPE, "Project C");
        // when
    	UnexpectedProjectTypeException exception =  assertThrows(UnexpectedProjectTypeException.class, ()-> {
    		ExpenseService.getExpenseCodeByProjectTypeAndName(project);
    	});
    	// then
    	assertEquals(exception.getMessage(), "You enter invalid project type");
    }
}