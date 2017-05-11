package net.rcarz.jiraclient;

import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * Represents an issue status category.
 */
public class StatusCategory extends Resource {

    private String colorName = null;
    private String key = null;
    private String name = null;

    /**
     * Creates a statusaCategory from a JSON payload.
     *
     * @param restclient REST client instance
     * @param json JSON payload
     */
    protected StatusCategory(RestClient restclient, JSONObject json) {
        super(restclient);

        if (json != null)
            deserialise(json);
    }

    private void deserialise(JSONObject json) {
        Map map = json;

        self = Field.getString(map.get("self"));
        id = String.valueOf(map.get("id"));
        key = Field.getString(map.get("key"));
        colorName = Field.getString(map.get("colorName"));
        name = Field.getString(map.get("name"));
    }

    /**
     * Retrieves the given status category record.
     *
     * @param restclient REST client instance
     * @param id Internal JIRA ID of the status category
     *
     * @return a status instance
     *
     * @throws JiraException when the retrieval fails
     */
    public static StatusCategory get(RestClient restclient, String id)
        throws JiraException {

        JSON result = null;

        try {
            result = restclient.get(getBaseUri() + "statuscategory/" + id);
        } catch (Exception ex) {
            throw new JiraException("Failed to retrieve status category" + id, ex);
        }

        if (!(result instanceof JSONObject))
            throw new JiraException("JSON payload is malformed");

        return new StatusCategory(restclient, (JSONObject)result);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getColorName() {
        return colorName;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
