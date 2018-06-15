import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from '../../model/User';
import {AuthService} from '../../services/auth.service';
import {MatDialog, MatPaginator, MatSnackBar} from '@angular/material';
import {AddGroupComponent} from './add-group/add-group.component';
import {LinkService} from '../../services/link.service';
import {Link} from '../../model/Link';
import {GroupInfoComponent} from './group-info/group-info.component';
import {CollectionViewer, DataSource, SelectionModel} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';
import {GroupsService} from '../../services/groups.service';
import {Test4GroupService} from '../../services/test4-group.service';
import {Test} from '../../model/Test';
import {TestService} from '../../services/test.service';
import {UploadService} from '../../services/upload.service';
import {EditProfileComponent} from '../../edit-profile/edit-profile.component';
import {AddTestComponent} from '../teacher-test/add-test/add-test.component';


@Component({
  selector: 'app-teacher-groups',
  templateUrl: './teacher-groups.component.html',
  styleUrls: ['./teacher-groups.component.css'],

})
export class TeacherGroupsComponent implements OnInit {
  selectedTest: Test;
  linkList: Link[];
  name: string;
  students: User[];
  initialSelection = [];
  allowMultiSelect = true;
  selection = new SelectionModel<User>(this.allowMultiSelect, this.initialSelection);
  displayedColumns = ['select', 'idUser', 'firstName', 'lastName'];
  dataSource: any;
  test4GroupData = [1];
  testName = [];
  teacherTest: any;
  selectGroup: string;
  selectedFile: File = null;
  Result: string[];
  selectedStudent: User;
  activate = false;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  panelOpenState = false;

  openDialog(): void {
    const dialogRef = this.dialog.open(AddGroupComponent, {
      width: '300px',
      data: {name: this.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.name = result;
      this.getGroups();
    });
  }

  constructor(private user: User, private authService: AuthService,
              public dialog: MatDialog,
              private linkService: LinkService,
              private groupInfo: GroupInfoComponent,
              private groupService: GroupsService,
              private test4group: Test4GroupService,
              private testService: TestService,
              private uploadService: UploadService,
              private snackBar: MatSnackBar) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));

  }


  editProfile(): void {
    this.dialog.open(EditProfileComponent, {
      width: '350px',
      data: {user: this.user = JSON.parse(localStorage.getItem('currentUser'))}
    });
  }

  ngOnInit() {
    this.getGroups();
  }

  getStudentsArray(user: User, link: Link) {
    this.linkService.getStudents(user.idUser, link.link).subscribe(data => this.students = data);
  }

  getGroups() {
    this.linkService.getGroups(this.user).subscribe(data => this.linkList = data);
  }

  getGroupTest(link: string) {

    this.test4GroupData.splice(0, this.test4GroupData.length);
    this.testName.splice(0, this.testName.length);

    this.test4group.getTestGroup(link).subscribe(data => {
      this.test4GroupData = data;
      this.getNameFromTest(this.test4GroupData);
    });
  }

  getNameFromTest(tests: any) {

    for (const test of tests) {
      for (const name of test) {
        this.testName.push(new TestInCombobox(name.idTest, name.name));
        break;
      }
    }
  }

  getAllTestTeacher(user: User, group: string) {
    this.testService.getTeacherTest(this.user, group).subscribe(data => {
      this.teacherTest = data;
    });
  }

  getStudents(link: Link) {
    this.selectGroup = link.link;
    this.getAllTestTeacher(this.user, this.selectGroup);
    this.getStudentsArray(this.user, link);
    this.dataSource = new StudentsDataSource(link, this.linkService, this.user);
    this.dataSource.paginator = this.paginator;
    this.dataSource.connect();
    this.getGroupTest(link.link);
  }

  logOut() {
    this.authService.logOut();
  }

  delete(link: Link) {
    this.linkService.delete(link).subscribe(data => {
      this.getGroups();
    });

  }

  addStudent(row) {
    for (const i of this.initialSelection) {
      if (i.idUser === row.idUser) {
        this.initialSelection.splice(this.initialSelection.indexOf(i), 1);
        return;
      }
    }
    this.initialSelection.push(row);
  }

  deleteStudents(link: Link) {
    for (const user of this.initialSelection) {
      this.groupService.deleteStudentFromGroup(user, link).subscribe(data => {
        this.dataSource = new StudentsDataSource(link, this.linkService, this.user);
        this.dataSource.connect();
        this.initialSelection.splice(0, this.initialSelection.length);
      });
    }


  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.students.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.forEach(row => this.selection.select(row));
  }

  addOldTest() {
    this.testService.addOldTest(this.selectGroup, this.selectedTest.idTest).subscribe(data => {
      this.getAllTestTeacher(this.user, this.selectGroup);
      this.getGroupTest(this.selectGroup);
      this.getNameFromTest(this.test4GroupData);
    });

  }

  deleteGroupTest() {
    this.testService.deleteGroupTest(this.selectGroup, this.selectedTest.idTest).subscribe(data => {
      this.getAllTestTeacher(this.user, this.selectGroup);
      this.getGroupTest(this.selectGroup);
      this.getNameFromTest(this.test4GroupData);
    });
  }

  onFileSelected(event) {
    this.activate = true;
    this.selectedFile = <File>event.target.files[0];
    this.uploadService.upload(this.selectedFile, this.selectedStudent, this.selectedTest).subscribe(data => {
        this.Result = data;
        if (data === 'ACCEPTED') {
          this.snackBar.open('Тест перевірено', 'Ура', {
            duration: 3000
          });
          this.activate = false;
          console.log(this.Result);
        } else if (data === 'CONFLICT') {
          this.activate = false;
          this.snackBar.open('Ця людина вже писала цей тест', 'Хмм.', {
            duration: 3000
          });
        } else {
          this.activate = false;
          this.snackBar.open('Проблеми з нейронною мережею', '401', {
            duration: 3000
          });
        }
      }
    );
  }


  saveStudent(student: User) {
    this.selectedStudent = student;
  }


  openTestWindow() {
    this.dialog.open(AddTestComponent, {
      width: '550px',
      data: {user: this.user = JSON.parse(localStorage.getItem('currentUser'))}
    });
  }
}

export class StudentsDataSource extends DataSource<any> {

  connect(): Observable<User[]> {
    return this.linkService.getStudents(this.user.idUser, this.link.link);
  }

  constructor(private link: Link, private linkService: LinkService, private user: User) {
    super();
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  disconnect(collectionViewer: CollectionViewer): void {
  }

}

export class TestInCombobox {
  idTest: number;
  name: string;


  constructor(idTest: number, name: string) {
    this.idTest = idTest;
    this.name = name;
  }
}

