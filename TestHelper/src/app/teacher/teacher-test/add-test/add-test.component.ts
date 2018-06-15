import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, Validators} from '@angular/forms';
import {Template} from '../../../model/Template';
import {TemplateService} from '../../../services/template.service';
import {User} from '../../../model/User';
import {TestService} from '../../../services/test.service';
import {UUID} from 'angular2-uuid';
import {MatSnackBar} from '@angular/material';

export interface StateGroup {
  letter: string;
  names: string[];
}

@Component({
  selector: 'app-add-test',
  templateUrl: './add-test.component.html',
  styleUrls: ['./add-test.component.css']
})
export class AddTestComponent implements OnInit {

  toppings = new FormControl();
  templates: any;
  selectedTemplate = [];
  selectedTemplates = [];
  enterName: string;
  selectedTemplateToDel: Template;


  constructor(public templService: TemplateService,
              private user: User,
              public newTemplate: Template,
              private fb: FormBuilder,
              private testService: TestService,
              private snackBar: MatSnackBar) {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  maskAnswer = [/[А-Д]/];


  NameFormControl = new FormControl('', [
    Validators.required,

  ]);
  NumberFormControl = new FormControl('', [
    Validators.required,
    Validators.max(12)
  ]);
  AnswerFormControl = new FormControl('', [
    Validators.required,
  ]);


  ngOnInit() {
    this.getTemplates();
  }

  replaceTemplate() {
    for (const template of this.templates) {
      if (this.selectedTemplate[this.selectedTemplate.length - 1].questionNum === template.questionNum) {
        this.templates.splice(this.templates.indexOf(template), 1);
      }
    }
    this.selectedTemplates.push(this.selectedTemplate[this.selectedTemplate.length - 1]);
  }

  deleteSelectedTemplate() {
    for (const template of this.selectedTemplates) {
      if (this.selectedTemplateToDel.questionNum === template.questionNum && template.answer === this.selectedTemplateToDel.answer) {
        this.selectedTemplates.splice(this.templates.indexOf(template), 1);
        this.templates.push(template);
      }
    }
  }

  deleteTemplate() {
    console.log(this.selectedTemplateToDel);
    this.templService.deleteTemplate(this.selectedTemplateToDel).subscribe(data => this.getTemplates());
  }

  addTemplate() {
    this.newTemplate.idTeacher = this.user.idUser;
    this.templService.addTemplate(this.newTemplate).subscribe(data => {
      this.getTemplates();
    });
  }

  getTemplates() {
    this.templService.getTemplates(this.user).subscribe(data => {
      this.templates = data;
    });
  }

  addTest() {
    const idTest = UUID.UUID();
    for (const template of this.selectedTemplates) {
      this.testService.addTest(idTest, this.enterName, template).subscribe(data => {
        console.log(data);
        this.snackBar.open('Тест додано', '', {
          duration: 3000
        });
      });
    }

  }
}




