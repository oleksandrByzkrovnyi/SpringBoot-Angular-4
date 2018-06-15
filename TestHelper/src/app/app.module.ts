import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TextMaskModule} from 'angular2-text-mask';
import {ChangeDetectorRef, NgModule} from '@angular/core';
import {
  MatAutocompleteModule, MatButtonModule, MatCardModule, MatCheckboxModule, MatDialogModule, MatDividerModule, MatExpansionModule,
  MatFormFieldModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule,
  MatProgressBarModule, MatRadioModule,
  MatSelectModule, MatSnackBarModule, MatStepper, MatStepperModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule,
} from '@angular/material';


import {AppComponent} from './app.component';

import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './main/main.component';
import {LoginComponent} from './login/login.component';
import {SingupComponent} from './singup/singup.component';
import {HeaderComponent} from './header/header.component';
import {LoginFormComponent} from './login/login-form/login-form.component';
import {SingupFormComponent} from './singup/singup-form/singup-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {HttpClientModule} from '@angular/common/http';

import {AccountService} from './services/account.service';
import {AuthService} from './services/auth.service';
import {TeacherMainComponent} from './teacher/teacher-main/teacher-main.component';
import {StudentMainComponent} from './student/student-main/student-main.component';


import {AuthGuard} from '../guard/auth.guard';
import {TooltipModule} from 'ng2-tooltip';
import {User} from './model/User';
import {RoleGuard} from '../guard/role.guard';
import {TeacherGroupsComponent} from './teacher/teacher-groups/teacher-groups.component';
import {GroupsService} from './services/groups.service';
import {Group} from './model/Group';
import {AddGroupComponent} from './teacher/teacher-groups/add-group/add-group.component';
import {LinkService} from './services/link.service';
import {Link} from './model/Link';
import {UUID} from 'angular2-uuid';
import {GroupInfoComponent} from './teacher/teacher-groups/group-info/group-info.component';
import {TeacherTestComponent} from './teacher/teacher-test/teacher-test.component';
import {AddTestComponent} from './teacher/teacher-test/add-test/add-test.component';
import {TemplateService} from './services/template.service';
import {Template} from './model/Template';
import {Test4GroupService} from './services/test4-group.service';
import {TestService} from './services/test.service';
import {UploadService} from './services/upload.service';
import {AddToGroupComponent} from './student/add-to-group/add-to-group.component';
import {UserService} from './services/user.service';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { ResultTestComponent } from './student/result-test/result-test.component';
import {ResultService} from './services/result.service';

const appRoutes: Routes = [
    {path: '', component: MainComponent},
    {path: 'login', component: LoginComponent},
    {path: 'singup', component: SingupComponent},
    {
      path: 'main_student', component: StudentMainComponent,
      canActivate: [AuthGuard, RoleGuard],
      data: {
        expectedRole: 'STUDENT'
      }
    },
    {
      path: 'main_teacher', component: TeacherMainComponent,
      canActivate: [AuthGuard, RoleGuard],
      data: {
        expectedRole: 'TEACHER'
      }
    },
    {
      path: 'teacher_groups', component: TeacherGroupsComponent,
      canActivate: [AuthGuard, RoleGuard],
      data: {
        expectedRole: 'TEACHER'
      }
    },
    {
      path: 'teacher_groups/add_group', component: AddGroupComponent,
      canActivate: [AuthGuard, RoleGuard],
      data: {
        expectedRole: 'TEACHER'
      }
    },
    {
      path: 'teacher_test', component: TeacherTestComponent,
      canActivate: [AuthGuard, RoleGuard],
      data: {
        expectedRole: 'TEACHER'
      }
    },
    {
      path: 'group_info', component: GroupInfoComponent,
      canActivate: [AuthGuard, RoleGuard],
      data: {
        expectedRole: 'TEACHER'
      }
    }


  ]
;


@NgModule({

  declarations: [
    AppComponent,
    MainComponent,
    LoginComponent,
    SingupComponent,
    HeaderComponent,
    LoginFormComponent,
    SingupFormComponent,
    TeacherMainComponent,
    StudentMainComponent,
    TeacherGroupsComponent,
    AddGroupComponent,
    GroupInfoComponent,
    TeacherTestComponent,
    AddTestComponent,
    AddToGroupComponent,
    EditProfileComponent,
    ResultTestComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    TooltipModule,
    MatPaginatorModule,
    MatToolbarModule,
    MatIconModule,
    MatRadioModule,
    MatExpansionModule,
    MatTableModule,
    MatCardModule,
    MatTabsModule,
    MatListModule,
    MatListModule,
    MatDividerModule,
    MatFormFieldModule,
    BrowserModule,
    MatTooltipModule,
    MatSnackBarModule,
    MatMenuModule,
    BrowserAnimationsModule,
    FormsModule,
    TextMaskModule,
    MatDialogModule,

    HttpModule,
    HttpClientModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatAutocompleteModule,
    MatProgressBarModule,
    MatSelectModule,
    MatInputModule,
    MatStepperModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}),
  ],
  entryComponents: [AddToGroupComponent, EditProfileComponent, AddTestComponent, ResultTestComponent],
  providers: [HttpClientModule,  ResultService, UserService, UploadService, TestService, Test4GroupService, TemplateService, Template, TeacherGroupsComponent, GroupInfoComponent, AccountService, AuthService, AuthGuard, User, Link, RoleGuard, LinkService, GroupsService, Group, UUID, Headers],
  bootstrap: [AppComponent]
})
export class AppModule {
}
