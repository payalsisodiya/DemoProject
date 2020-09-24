package Files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String args[])
    {
        JsonPath js = new JsonPath(Payload.CorsePrice());

        //Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print Purchase amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //Print Title of the first course
        String titleFirstcourse = js.get("courses[0].title");
        System.out.println(titleFirstcourse);

        //Print All course titles and their respective Prices
        for(int i=0;i<count;i++)
        {
            String courseTitle = js.get("courses["+i+"].title");
            System.out.println(courseTitle);
            System.out.println(js.get("courses["+i+"].price").toString());
        }

        //5. Print no of copies sold by RPA Course
        System.out.println("Print no of copies sold by RPA Course");
        for(int i=0;i<count;i++)
        {
            String courseTitle = js.get("courses["+i+"].title");
            if(courseTitle.equalsIgnoreCase("RPA"))
            {
                int copies = js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }
        }
    }
}

