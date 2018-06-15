import {Component, OnInit} from '@angular/core';
import {User} from '../../model/User';
import {ResultService} from '../../services/result.service';
import {Test} from '../../model/Test';

@Component({
  selector: 'app-result-test',
  templateUrl: './result-test.component.html',
  styleUrls: ['./result-test.component.css']
})
export class ResultTestComponent implements OnInit {


  constructor(private resultService: ResultService) {
  }

  user: User;
  studentTest: Test[];
  selectedTest: Test;
  resultData: ResultData;

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.getTest();
  }


  getTest() {
    this.resultService.getStudentTest(this.user).subscribe(data => this.studentTest = data);
  }

  getResult() {
    this.resultService.getStudentResult(this.user, this.selectedTest.idTest).subscribe(data => {
        this.resultData = data;
        console.log(data);
      }
    )
    ;
  }
}

export class ResultData {
  public numAll: string;
  public numCorrect: string;
}


