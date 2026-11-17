import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Curriculums } from './curriculums';

describe('Curriculums', () => {
  let component: Curriculums;
  let fixture: ComponentFixture<Curriculums>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Curriculums],
    }).compileComponents();

    fixture = TestBed.createComponent(Curriculums);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
