import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-add-team-dialog',
  templateUrl: './add-team-dialog.component.html',
  styleUrls: ['./add-team-dialog.component.css']
})
export class AddTeamDialogComponent {

  addTeamForm! : FormGroup;

  constructor(private formBuilder: FormBuilder, public dialogRef: MatDialogRef<AddTeamDialogComponent>) {
    this.createForm();
  }

  createForm(): void {
    this.addTeamForm = this.formBuilder.group({
      name: ['', Validators.required],
      wins: [null, [Validators.min(0)]],
      losses: [null, [Validators.min(0)]],
      pointsScored: [null, [Validators.min(0)]],
      pointsAllowed: [null, [Validators.min(0)]],
    });
  }


  onSubmit() {
    if (this.addTeamForm.valid) {
      if (!this.addTeamForm.value.wins) {
        this.addTeamForm.value.wins = 0
      }
      if (!this.addTeamForm.value.losses) {
        this.addTeamForm.value.losses = 0
      }
      if (!this.addTeamForm.value.pointsScored) {
        this.addTeamForm.value.pointsScored = 0
      }
      if (!this.addTeamForm.value.pointsAllowed) {
        this.addTeamForm.value.pointsAllowed = 0
      }
      this.dialogRef.close(this.addTeamForm.value)
    }
    else {
      if (!this.addTeamForm.value.name) {
        confirm("Name cannot be empty.")
      }
    }
  }
}
