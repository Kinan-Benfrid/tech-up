package episen.si.ing1.pds.client.view.WindowConfig;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlgoWindow {


    String window_id;

    int blind_level_start;
    int blind_percentage_start;
    int blind_level_add;
    int blind_percentage_add;

    int opacity_level_start;
    int opacity_percentage_start;
    int opacity_level_add;
    int opacity_percentage_add;


    //data_sensor
    int outside_temperature, level_sunlight, room_temperature;

    //window state to send
    private String window, blind, opacityW, tempI;

    public AlgoWindow(Map<String, Integer> h) {


        Map<String, Integer> hm = h;

        blind_level_start = (hm.get("blind_level_start"));
        blind_percentage_start = (hm.get("blind_percentage_start"));
        blind_level_add = (hm.get("blind_level_add"));
        blind_percentage_add = (hm.get("blind_percentage_add"));

        opacity_level_start = (hm.get("opacity_level_start"));
        opacity_percentage_start = (hm.get("opacity_percentage_start"));
        opacity_level_add = (hm.get("opacity_level_add"));
        opacity_percentage_add = (hm.get("opacity_percentage_add"));

        level_sunlight = (hm.get("level_sunlight"));
    }

    public List algoWindow() {
        // String window
        List list = new List();
        list.add(String.valueOf(level_sunlight));

        if (level_sunlight < blind_level_start) {
            this.blind = "0";
        } else {
            this.blind = "100";
            int countPercentage = blind_percentage_start;
            int countSunlight = blind_level_start;
            boolean bool = false;
            while (countPercentage < 100 || !bool) {
                if (level_sunlight >= countSunlight && level_sunlight < countSunlight + blind_level_add) {
                    bool = true;
                    this.blind = "" + countPercentage;
                }
                countPercentage += blind_percentage_add;
                countSunlight += blind_level_add;
            }
        }
        list.add(this.blind);

        //String opacityW
        if (level_sunlight < opacity_level_start) {
            this.opacityW = "0";
        } else {
            this.opacityW = "100";
            int countPercentage = opacity_percentage_start;
            int countSunlight = opacity_level_start;
            boolean bool = false;
            while (countPercentage < 100 || !bool) {
                if (level_sunlight >= countSunlight && level_sunlight < countSunlight + opacity_level_add) {
                    bool = true;
                    this.opacityW = "" + countPercentage;
                }
                countPercentage += opacity_percentage_add;
                countSunlight += opacity_level_add;
            }
        }
        list.add(this.opacityW);//pour recupere les valeurs


       /*String requestU = "update windows set window_state="+this.window+",percentage_blind="+this.blind+",percentage_opacity="+this.opacityW+", room_temperature="+this.tempI+" where id="+window_id;
        cc.sendRequest(requestU);*/

        return list;
    }
}
