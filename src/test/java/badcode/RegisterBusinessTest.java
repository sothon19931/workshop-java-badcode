package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessTest {

    // ========= Test to fail. ===========
    @Test
    @DisplayName("ไม่มีการกำหนด name => Exception First name is required.")
    public  void Case01 () {
        //Arrange
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException ex) {
            if (!ex.getMessage().equals("First name is required")) {
            }
        }
    }
    @Test
    @DisplayName("ไม่มีการกำหนด last name => Exception Last name is required.")
    public  void Case02 () {
        //Arrange
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("Sothon");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException ex) {
            if (!ex.getMessage().equals("Last name is required.")) {
            }
        }
    }
    @Test
    @DisplayName("ไม่มีการกำหนด email => Exception Email is required.")
    public  void Case03 () {
        //Arrange
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("Sothon");
            speaker.setLastName("Kalachack");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException ex) {
            if (!ex.getMessage().equals("Email is required.")) {
            }
        }
    }
    @Test
    @DisplayName("ไม่มีการกำหนด email => Exception Invalid Email .")
    public  void Case04 () {
        //Arrange
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("Sothon");
            speaker.setLastName("Kalachack");
            speaker.setEmail("xxxxxx");
            registerBusiness.register(null, speaker);
            fail();
        } catch (DomainEmailInvalidException ex) {
        }
    }
    @Test
    @DisplayName("Exception Speaker doesn't meet our standard rules.")
    public  void Case05 () {
        //Arrange
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("Sothon");
            speaker.setLastName("Kalachack");
            speaker.setEmail("sothon@xxx.com");
            registerBusiness.register(null, speaker);
            fail();
        } catch (SpeakerDoesntMeetRequirementsException ex) {
            if (!ex.getMessage().equals("Speaker doesn't meet our standard rules.")) {
                fail(ex.getMessage());
            }
        }
    }
    @Test
    @DisplayName("Exception Can't save a speaker.")
    public  void Case06 () {
        //Arrange
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("Sothon");
            speaker.setLastName("Kalachack");
            speaker.setEmail("sothon@gmail.com");
            registerBusiness.register(null, speaker);
        } catch (SaveSpeakerException ex) {
            if (!ex.getMessage().equals("Can't save a speaker.")) {
                fail(ex.getMessage());
            }
        }
    }


    // ========= Test to pass. ===========

    private class SaveSpeaker implements SpeakerRepository {
        @Override
        public Integer saveSpeaker(Speaker speaker) {
            return 1000;
        }
    }
    @Test
    @DisplayName("บันทึกข้อมูลได้")
    public  void Case07 () {
        //Arrange
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            int speakerId = registerBusiness.register(new SaveSpeaker(), speaker);
            assertEquals(1000, speakerId);
        } catch (ArgumentNullException ex) {
        }
    }
}
